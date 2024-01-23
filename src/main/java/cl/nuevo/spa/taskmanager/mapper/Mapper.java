package cl.nuevo.spa.taskmanager.mapper;

/**
 * The interface Mapper.
 *
 * @param <T> the type parameter
 * @param <R> the type parameter
 */
public interface Mapper<T, R> {

  /**
   * Map r.
   *
   * @param t the t
   * @return the r
   */
  R map(T t);
}
