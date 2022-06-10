$(document).ready(function() {

});

async function iniciarSesion(){

    let datos={};
    datos.email=document.getElementById('txtEmail').value;
    datos.contrasenia=document.getElementById('txtPassword').value;
    console.log(datos.email+" "+datos.contrasenia);
    const request = await fetch('api/login', {
           method: 'POST',
           headers: {
                     'Accept': 'application/json',
                     'Content-Type': 'application/json'
                     },
           body:JSON.stringify(datos)
          });
          const respuesta=await request.text();
          if(respuesta!="Fail")
          {
             console.log(respuesta);
             localStorage.token=respuesta;
             localStorage.email=datos.email;
             window.location.href="usuarios.html";
          }
          else
          {
            alert("Las credenciales son incorrectas. Vuelva a intentarlo");
          }
}
