/**
 * @ Author: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Create Time: 2024-01-11 19:18:40
 * @ Modified by: Eduardo 'Ph1L' Rodríguez Bahamonde
 * @ Modified time: 2024-01-11 20:52:18
 * @ Description:
 */

package spa.nuevo.desafiotecnico.repository;

import spa.nuevo.desafiotecnico.model.EstadoTarea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Integer> {

}
