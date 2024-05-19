
function eliminar(pacienteId) {

    confirm('¿Estas seguro de eliminar al paciente?')
    if (confirm) {
        fetch('/paciente/eliminar/' + pacienteId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                alert('Paciente eliminado')
                window.location.reload()
            } else {
                console.error('Error al eliminar: ' + response.statusText);
            }
        })
        .catch(error => {
            console.error('Error al eliminar:', error);
        });
    }
}


