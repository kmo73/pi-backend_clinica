
function modificar(pacienteId) {
    let formularioMod = document.getElementById('formularioModificable')

    formularioMod.classList.remove('formularioModificable')

    fetch('/paciente/' + pacienteId, {
        method: 'GET'
    })
    .then(response => response.json())
    .then(data => {
        let paciente = data;
        document.getElementById('id').value = paciente.id
        document.getElementById('nombre').value = paciente.nombre
        document.getElementById('apellido').value = paciente.apellido
        document.getElementById('cedula').value = paciente.cedula
        document.getElementById('email').value = paciente.email
        document.getElementById('fechaIngreso').value = paciente.fechaIngreso
        document.getElementById('calle').value = paciente.domicilio.calle
        document.getElementById('numero').value = paciente.domicilio.numero
        document.getElementById('localidad').value = paciente.domicilio.localidad
        document.getElementById('provincia').value = paciente.domicilio.provincia

    })

}

let formularioMod = document.getElementById('formularioModificable')
    formularioMod.addEventListener('submit', function (e) {
        e.preventDefault()

        let dataModificada = {
            id: document.getElementById('id').value,
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value,
            cedula: document.getElementById('cedula').value,
            email: document.getElementById('email').value,
            fechaIngreso: document.getElementById('fechaIngreso').value,
            domicilio: {
            calle: document.getElementById('calle').value,
            numero: document.getElementById('numero').value,
            localidad: document.getElementById('localidad').value,
            provincia: document.getElementById('provincia').value
            }
        }
        console.log(dataModificada.calle + "DATA MODIFICADA PREVIO AL PUT FINAL")
        fetch('/paciente', {
            method: 'PUT',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify(dataModificada)
        })
        .then(response => {
            console.log(response);
            document.querySelector('.pacienteModificado').innerHTML = 'Paciente modificado'
        } )
        setTimeout(() => {
            window.location.reload()
        }, 2000);
    })

            
