function modificar(turnoId) {
    let formularioMod = document.getElementById('formularioModificable')

    formularioMod.classList.remove('formularioModificable')

    fetch('/turnos/' + turnoId, {
        method: 'GET'
    })
    .then(response => response.json())
    .then(data => {
        let turno = data;
        document.getElementById('id').value = turno.id
        document.getElementById('paciente').value = turno.paciente.id
        document.getElementById('odontologo').value = turno.odontologo.id
        document.getElementById('fechaTurno').value = turno.fechaTurno
    })
}

let formularioMod = document.getElementById('formularioModificable')
    formularioMod.addEventListener('submit', async function (e) {
        e.preventDefault()

        let dataModificada = await obtenerDatos()

        fetch('/turnos', {
            method: 'PUT',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify(dataModificada)
        })
        .then(response => {
            document.querySelector('.turnoModificado').innerHTML = 'Turno modificado'
        } )
        setTimeout(() => {
            window.location.reload()
        }, 2000)
    })


    async function obtenerDatos() {
        let campoPaciente = document.getElementById('paciente').value;
        let campoOdonto = document.getElementById('odontologo').value;
    
        // Realizar ambas solicitudes fetch simultáneamente
        let responsePaciente = fetch('/paciente/' + campoPaciente);
        let responseOdontologo = fetch('/odontologo/' + campoOdonto);
    
        // Esperar a que ambas solicitudes se completen
        let dataPaciente = await responsePaciente.then(response => response.json());
        let dataOdontologo = await responseOdontologo.then(response => response.json());
    
        // Crear los objetos una vez que los datos estén disponibles
        paciente = {
            id: dataPaciente.id,
            nombre: dataPaciente.nombre,
            apellido: dataPaciente.apellido,
            cedula: dataPaciente.cedula,
            fechaIngreso: dataPaciente.fechaIngreso,
            email: dataPaciente.email,
            domicilio: {
                calle: dataPaciente.domicilio.calle,
                numero: dataPaciente.domicilio.numero,
                localidad: dataPaciente.domicilio.localidad,
                provincia: dataPaciente.domicilio.provincia
                    }
        };
    
        odontologo = {
            id: dataOdontologo.id,
            matricula: dataOdontologo.matricula,
            nombre: dataOdontologo.nombre,
            apellido: dataOdontologo.apellido
        };
    
        // Crear el objeto dataAGuardar con los datos obtenidos
        let dataModificada = {
            id: document.getElementById('id').value = turno.id,
            paciente: paciente,
            odontologo: odontologo,
            fechaTurno: document.getElementById('fechaTurno').value
        };
    
        // Usar dataAGuardar para cualquier operación adicional
        console.log(dataModificada);
    
        return dataModificada
    }