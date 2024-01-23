package cl.nuevo.spa.taskmanager.mapper;

/**
 * The interface Bi mapper.
 *
 * @param <T> the type parameter
 * @param <U> the type parameter
 * @param <R> the type parameter
 */
public interface BiMapper<T, U, R> {

  /**
   * Map r.
   *
   * @param t the t
   * @param u the u
   * @return the r
   */
  R map(T t, U u);
}
