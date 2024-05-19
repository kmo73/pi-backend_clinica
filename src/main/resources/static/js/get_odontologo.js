window.addEventListener('load', function(){

    fetch('/odontologo/listar')
    .then(response => response.json())
    .then(data => {

        for(odontologo of data){
            let tabla = document.querySelector('.listBody')
            tabla.innerHTML +=  '<tr>' +
                                    '<td class="odontologoId">' + odontologo.id + '</td>' +
                                    '<td>' + odontologo.matricula + '</td>' +
                                    '<td>' + odontologo.nombre + '</td>' +
                                    '<td>' + odontologo.apellido + '</td>' +
                                    '<td class="botonera">' +
                                        '<button onclick="modificar(' + odontologo.id + ')" class="putButton">Actualizar</button>' +
                                        '<button onclick="eliminar(' + odontologo.id + ')" class="deleteButton">X</button>' +
                                    '</td>' +
                                '</tr>'
                                console.log(odontologo.id);
        }
    })
})