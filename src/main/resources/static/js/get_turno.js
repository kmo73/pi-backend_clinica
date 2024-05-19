window.addEventListener('load', function(){

    fetch('/turnos/listar')
    .then(response => response.json())
    .then(data => {

        for(turno of data){
            console.log(turno);
            let tabla = document.querySelector('.listBody')
            tabla.innerHTML +=  '<tr>' +
                                    '<td class="turnoId">' + turno.id + '</td>' +
                                    '<td>' + turno.paciente.id + '</td>' +
                                    '<td>' + turno.odontologo.id + '</td>' +
                                    '<td>' + turno.fechaTurno + '</td>' +
                                    '<td class="botonera">' +
                                        '<button onclick="modificar(' + turno.id + ')" class="putButton">Actualizar</button>' +
                                        '<button onclick="eliminar(' + turno.id + ')" class="deleteButton">X</button>' +
                                    '</td>' +
                                '</tr>'
        }
    })
}) 