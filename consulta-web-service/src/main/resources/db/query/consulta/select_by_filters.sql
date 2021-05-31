select id, usuario_id, exame_id, laboratorio_id from consulta where usuario_id = ? and exame_id = ? and laboratorio_id = ? limit 1;
