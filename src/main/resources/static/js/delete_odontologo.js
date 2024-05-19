function eliminar(odontologoId) {
    if (confirm('¿Estas seguro de eliminar al odontólogo?')) {
        fetch('/odontologo/eliminar/' + odontologoId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                alert('Odontólogo eliminado');
                window.location.reload();
            } else {
                console.error('Error al eliminar: ' + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error al eliminar:', error);
        });
    }
}


