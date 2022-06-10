// Call the dataTables jQuery plugin
$(document).ready(function() {

 cargarUsuarios();
  $('#usuarios').DataTable();

});

async function cargarUsuarios(){

  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':localStorage.token;
    }

  });

  const usuarios = await request.json();

  let listadoUsuarios='';
  for (let user of usuarios)
  {
  let botonEliminar='<a href="#" onclick="eliminarUsuario('+user.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
   let usuarioHTML='<tr><td>'+user.id+'</td><td>'+user.nombre+'</td><td>'+user.apellidos+'</td><td>'+
                    user.email+'</td><td>'+user.telefono+'</td><td>'+botonEliminar+'</td></tr>';
  listadoUsuarios+=usuarioHTML;
  }
  document.querySelector('#usuarios tbody').outerHTML=listadoUsuarios;
}

async function eliminarUsuario(id)
{
     if(!confirm("¿Deseaa eliminar el usuario?"))
     {
        return
     }
     const request = await fetch('api/usuarios/'+id, {
       method: 'DELETE',
       headers: {
         'Accept': 'application/json',
         'Content-Type': 'application/json',
         'Authorization':localStorage.token;
       }

     });
      location.reload();
}