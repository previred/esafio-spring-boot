/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:07:41
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-11 19:58:17
 * @ Description:
 */

package spa.nuevo.desafiotecnico.repository;

import spa.nuevo.desafiotecnico.model.Tarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}
