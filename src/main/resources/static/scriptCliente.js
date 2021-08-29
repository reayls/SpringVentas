document.addEventListener("click", (e) => {
    if (e.target.matches(".edit"))
    {
        editarProducto(e);
    }
    if (e.target.matches("#nuevoCliente"))
    {
        let editModal = new bootstrap.Modal(document.getElementById('editModal'));
        editModal.show();
    }
})
function editarProducto(e){
    let idproducto = e.target.getAttribute("data-idcliente");
    let editModal = new bootstrap.Modal(document.getElementById('editModal'));
    ajax({method: "Get",
        url: `/clientes/editar/${idproducto}`,
        success: (resp) => {
            document.getElementById('id').value=resp.id;
            document.getElementById('nombre').value=resp.nombre;
            document.getElementById('apellido').value=resp.apellido;
            document.getElementById('direccion').value=resp.direccion;
            editModal.show();
        },
        error: (err) => {
            console.error("error en ", err)
        },
    });
}

const ajax = (options) => {
    let {method, url, success, error, data} = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", e => {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status >= 200 && xhr.status < 300) {
            let objson = JSON.parse(xhr.response);
            success(objson);
        } else {
            let message = xhr.statusText || "ocurrio un error";
            error(`Error ${xhr.status} : ${message}`);
        }
    });
    xhr.open(method || "GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
}
