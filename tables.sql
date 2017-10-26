/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  2110111
 * Created: Oct 26, 2017
 */

CREATE TABLE CM_EPS (
`nombre` varchar(100)  NOT NULL,
`nit` varchar(20)  NOT NULL PRIMARY KEY
);


CREATE TABLE `CM_PACIENTES` (
  `id` int(11) NOT NULL,
  `tipo_id` varchar(2) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `EPS_nit` varchar(20) NOT NULL,
  PRIMARY KEY (`id`,`tipo_id`)
);


CREATE TABLE `CM_CONSULTAS` (
  `idCONSULTAS` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_y_hora` datetime NOT NULL,
  `resumen` varchar(200) NOT NULL,  
  `costo` int(11) NOT NULL,
  `PACIENTES_id` int(11) NOT NULL DEFAULT '0',
  `PACIENTES_tipo_id` varchar(2) NOT NULL DEFAULT 'cc',
  PRIMARY KEY (`idCONSULTAS`)
  ); 