window.addEventListener('load', function(){ 

    let formulario = document.querySelector('.formulario')

    formulario.addEventListener('submit', function (e){
        e.preventDefault()

        let dataAGuardar = {
            matricula: document.getElementById('matricula').value,
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value
        }

        fetch('/odontologo/crear', {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify(dataAGuardar)
        })
        .then(response => response.json())
        .then(data => {
            document.querySelector('.agregadoOdonto').innerHTML = 'Odontólogo agregado'
        })
        .catch(error =>{
            console.error('Error al mostrar los datos: ', error);
        })
    })
})