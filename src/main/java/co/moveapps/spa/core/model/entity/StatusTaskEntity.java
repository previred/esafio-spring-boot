/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.moveapps.spa.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author andresduran0205@gmail.com
 */
@Entity
@Table(name = "status_task", schema = "core")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusTaskEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "enable")
    private Boolean enable;

}
