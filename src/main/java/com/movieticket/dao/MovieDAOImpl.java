package com.movieticket.dao;

import com.Connection.HibernateConf;
import com.movieticket.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    private SessionFactory factory = HibernateConf.getFactory();

    @Override
    public List<Movie> getAllMovies(){
        List<Movie> movies = new ArrayList<>();
        try (Session session = factory.openSession()) {
            movies = session.createQuery("FROM Movie", Movie.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void updateMovieRating(int movieId) {
        try (Session session = HibernateConf.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            String sql = "UPDATE movies m " +
                    "JOIN ( " +
                    "    SELECT movie_id, AVG(rating) AS avg_rating " +
                    "    FROM reaction_movie " +
                    "    GROUP BY movie_id " +
                    ") rm ON m.movie_id = rm.movie_id " +
                    "SET m.rating = rm.avg_rating " +
                    "WHERE m.movie_id = :movieId";

            int rowCount = session.createNativeQuery(sql)
                    .setParameter("movieId", movieId)
                    .executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Movie getMovieById(int movieId){
        Movie movie = null;
        try (Session session = factory.openSession()) {
            movie = session.get(Movie.class, movieId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public List<Movie> getRatingMovies(){
        List<Movie> movies = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Movie WHERE rating > 3 ORDER BY rating DESC");
            movies = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public int getPrice(Movie movieId) {
        int price = 0;
        try (Session session = factory.openSession()) {
            Movie movie = session.get(Movie.class, movieId);
            if (movie != null) {
                price = movie.getPrice();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    @Override
    public String getName(Movie movieId) {
        String name = null;
        try (Session session = factory.openSession()) {
            Movie movie = session.get(Movie.class, movieId);
            if (movie != null) {
                name = movie.getTitle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public void deleteMovie(Movie movieId){
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, movieId);
            if (movie != null) {
                session.delete(movie);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> searchMovie(String title){
        List<Movie> movies = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Movie WHERE title = :title");
            query.setParameter("title", title);
            movies = query.getResultList();
            // Handle search results here
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<Movie> getRecommendedMovies() {
        List<Movie> recommendedMovies = new ArrayList<>();
        try (Session session = factory.openSession()) {
            String sql = "SELECT m.* " +
                    "FROM movies m " +
                    "JOIN (SELECT movie_id, COUNT(*) AS reservation_count " +
                    "      FROM reservations " +
                    "      GROUP BY movie_id " +
                    "      HAVING COUNT(*) > 3) AS r " +
                    "ON m.movie_id = r.movie_id " +
                    "LIMIT 6";
            Query query = session.createNativeQuery(sql, Movie.class);
            recommendedMovies = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedMovies;
    }

    @Override
    public void addMovie(Movie movie){
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
