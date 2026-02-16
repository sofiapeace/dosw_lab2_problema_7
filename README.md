<img width="1542" height="942" alt="reto7sol" src="https://github.com/user-attachments/assets/baa35b3a-67a6-4195-8877-1b17e277ae2e" />

 
 **Descripción del Problema**
 Has comprado un control remoto mágico que permite al usuario ejecutar acciones sobre varios dispositivos del hogar: luces, puertas, música y persianas. Cada acción puede tener parámetros específicos y puede ser deshecha después de ejecutarse. Alguien ha desconfigurado los electrodomésticos del hogar y tu tarea es usar el control para probar, ejecutar y deshacer acciones, para finalmente descubrir quién desconfiguró cada aparato.
 
 **Condiciones**
  Cada acción puede tener parámetros (por ejemplo: ajustar volumen o persiana).Permitir ejecutar $X$ acciones y registrar quién las ejecutó (usuario).Registrar un historial completo para revisar quién desconfiguró qué.Permitir deshacer cualquier acción individual.Mostrar un resumen final con las acciones ejecutadas y quién las hizo.
  
  **Detalles del Patrón de Diseño**
  De acuerdo a los requerimientos del laboratorio, se aplicó la siguiente solución técnica:Patrón de Diseño: Patrones de Comportamiento.Patrón Utilizado: Command (Comando).Justificación: Se utilizó este patrón porque permite encapsular una petición como un objeto, lo que facilita parametrizar a los clientes con diferentes peticiones, encolar peticiones y, lo más importante, soportar operaciones que se pueden deshacer (undo). Es ideal para sistemas que requieren un historial de acciones y la capacidad de revertirlas al estado anterior.Cómo lo aplico: 
  1.  Se definió una interfaz Command con métodos para ejecutar y deshacer acciones.
  2.  Se crearon comandos concretos para cada dispositivo (Luz, Puerta, Música, Persiana) que encapsulan la lógica de negocio.
  3.  Se implementó un historial que almacena objetos de comando junto con el nombre del usuario, permitiendo revertir el estado del electrodoméstico y generar estadísticas finales sobre quién realizó cada alteración.

