window.addEventListener('load', function(){

    fetch('/paciente/listar')
    .then(response => response.json())
    .then(data => {

        for(paciente of data){
            let tabla = document.querySelector('.listBody')
            tabla.innerHTML +=  '<tr>' +
                                    '<td class="pacienteId">' + paciente.id + '</td>' +
                                    '<td>' + paciente.nombre + '</td>' +
                                    '<td>' + paciente.apellido + '</td>' +
                                    '<td>' + paciente.cedula + '</td>' +
                                    '<td>' + paciente.email + '</td>' +
                                    '<td>' + paciente.domicilio.calle + '</td>' +
                                    '<td>' + paciente.domicilio.numero + '</td>' +
                                    '<td>' + paciente.domicilio.localidad + '</td>' +
                                    '<td>' + paciente.domicilio.provincia + '</td>' +
                                    '<td class="botonera">' +
                                        '<button onclick="modificar(' + paciente.id + ')" class="putButton">Actualizar</button>' +
                                        '<button onclick="eliminar(' + paciente.id + ')" class="deleteButton">X</button>' +
                                    '</td>' +
                                '</tr>'
        }
    })
}) 