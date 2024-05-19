
function modificar(odontologoId) {
    let formularioMod = document.getElementById('formularioModificable')

    formularioMod.classList.remove('formularioModificable')

    fetch('/odontologo/' + odontologoId, {
        method: 'GET'
    })
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        document.getElementById('id').value = odontologo.id
        document.getElementById('matricula').value = odontologo.matricula
        document.getElementById('nombre').value = odontologo.nombre
        document.getElementById('apellido').value = odontologo.apellido
    })
}

let formularioMod = document.getElementById('formularioModificable')
    formularioMod.addEventListener('submit', function (e) {
        e.preventDefault()

        let dataModificada = {
            id: document.getElementById('id').value,
            matricula: document.getElementById('matricula').value,
            nombre: document.getElementById('nombre').value,
            apellido: document.getElementById('apellido').value
        }

        fetch('/odontologo', {
            method: 'PUT',
            headers: {
                'Content-Type' : 'application/json',
            },
            body: JSON.stringify(dataModificada)
        })
        .then(response => {
            document.querySelector('.odontoModificado').innerHTML = 'Odontólogo modificado'
        } )
        setTimeout(() => {
            window.location.reload()
        }, 2000);
    })

            
