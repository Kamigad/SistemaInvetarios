package gm.inventario.excepcion;

import java.time.LocalDateTime;

public record ErrorRespuesta (String mensaje, LocalDateTime fecha) {}
