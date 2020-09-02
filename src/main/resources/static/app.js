function eliminarContacto(idContacto) {
    if (!confirm('¿Está seguro de eliminar el contacto?')) {
        return;
    }

    $('#form-eliminar-contacto-' + idContacto)
        .submit();
}