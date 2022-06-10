$(document).ready(function() {

});

async function registrarUsuarios(){

    let datos={};
    datos.nombre=document.getElementById('txtNombre').value;
    datos.apellidos=document.getElementById('txtApellido').value;
    datos.email=document.getElementById('txtEmail').value;
    datos.contrasenia=document.getElementById('txtPassword').value;
    datos.telefono="123456789";


          const request = await fetch('api/usuarios', {
                    method: 'POST',
                    headers: {
                          'Accept': 'application/json',
                          'Content-Type': 'application/json'
                        },
                   body:JSON.stringify(datos)
                  });
}
