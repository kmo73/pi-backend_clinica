function eliminar(turnoId) {

    confirm('¿Estas seguro de eliminar al turno?')
    if (confirm) {
        fetch('/turnos/' + turnoId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => {
            if (response.ok) {
                alert('Turno eliminado')
                window.location.reload()
            } else {
                console.error('Error al eliminar: ' + response.statusText)
            }
        })
        .catch(error => {
            console.error('Error al eliminar:', error);
        })
    }
}