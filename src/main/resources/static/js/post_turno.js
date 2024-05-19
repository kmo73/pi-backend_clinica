
window.addEventListener('load', function(){ 

    let formulario = document.querySelector('.formulario')

    formulario.addEventListener('submit', async function (e){
        e.preventDefault()

        let dataAGuardar = await obtenerDatos()

        console.log(dataAGuardar.odontologo.matricula + " Data a guardar");
        fetch('/turnos', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify(dataAGuardar)

        })

        .then(response => response.json())
        .then(data => {
            document.querySelector('.agregadoTurno').innerHTML = 'Turno agendado'
        })
        .catch(error =>{
            console.error('Error al mostrar los datos: ', error);
        })
    })
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
    let dataAGuardar = {
        paciente: paciente,
        odontologo: odontologo,
        fechaTurno: document.getElementById('fechaTurno').value
    };

    // Usar dataAGuardar para cualquier operación adicional
    console.log(dataAGuardar);

    return dataAGuardar;
}
