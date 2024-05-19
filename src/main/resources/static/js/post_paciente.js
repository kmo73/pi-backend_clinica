window.addEventListener('load', function(){ 

    let formulario = document.querySelector('.formulario')

    formulario.addEventListener('submit', function (e){
        e.preventDefault()

        let dataAGuardar = {
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value,
            cedula: document.getElementById('cedula').value,
            fechaIngreso: document.getElementById('fechaIngreso').value,
            email: document.getElementById('email').value,
            domicilio: {
            calle: document.getElementById('calle').value,
            numero: document.getElementById('numero').value,
            localidad: document.getElementById('localidad').value,
            provincia: document.getElementById('provincia').value
            }
        }

        fetch('/paciente/crear', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify(dataAGuardar)
        })
        .then(response => response.json())
        .then(data => {
            document.querySelector('.agregadoPaciente').innerHTML = 'Paciente agregado'
            console.log(data)
        })
        .catch(error =>{
            console.error('Error al mostrar los datos: ', error);
        })
    })
})