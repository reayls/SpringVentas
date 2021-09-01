
document.addEventListener("click", (e) => {
    //console.log("click en textt",e.target);

    if (e.target.matches(".quitar"))
    {
        console.log(e.target.getAttribute("data-idetalle"));
        quitarDecarrito(e);
    }
    if (e.target.matches(".addcar"))
    {
        console.log(e.target.getAttribute("data-idproducto"));
        addCarrito(e);
    }
    if (e.target.matches("#btcliente"))
    {
        addCliente();
    }
})
function addCliente(){
    let idcl=document.getElementById('cliente.id').value;
    let name=document.getElementById('namecliente');
    ajax({method: "GET",
        url: `/clientes/buscar/${idcl}`,
        success: (resp) => {
            if(resp!=null){
                console.log(resp);
                name.textContent=`${resp.nombre} ${resp.apellido}`;
            }else{
                name.textContent=`Cliente no Encontrado`;
            }
        },
        error: (err) => {
            console.error("error en ", err)
        },
        data: null,
    });
}
function quitarDecarrito(e) {
    let idetalle = e.target.getAttribute("data-idetalle");
    ajax({method: "Get",
        url: `/ventas/quitar?indice=${idetalle}`,
        success: (resp) => {
            pintarDetalle(resp);
        },
        error: (err) => {
            console.error("error en ", err)
        },
    });
    console.log(JSON.stringify({indice: idetalle}));
    let id = e.target.parentNode.parentNode;
    id.remove();

}
function addCarrito(e) {
    let idproducto = e.target.getAttribute("data-idproducto");
    ajax({method: "GET",
        url: `/ventas/carrito?id=${idproducto}&cantidad=5`,
        success: (resp) => {
            pintarDetalle(resp);
        },
        error: (err) => {
            console.error("error en ", err)
        },
        data: null,
    });
}
function pintarDetalle(resp) {
    const  tbo = document.getElementById("tbod");
    const  tf = document.getElementById("totalfinal");
    let totalfinal=0;
    tbo.innerHTML = " ";
    console.log(resp);
    resp.forEach((elemento, index) => {
        let str = `<tr>
                        <td>${index + 1}</td>
                        <td>${elemento.producto.nombre}</td>
                        <td>${elemento.precio}</td>
                        <td>${elemento.cantidad}</td>
                        <td>${elemento.totalxproducto}</td>
                        <td><button class="btn btn-danger quitar" type="button" data-idetalle="${index}">Quitar</button></td>
                    </tr>`;
        tbo.insertAdjacentHTML("beforeend", str);
        totalfinal+=elemento.precio*elemento.cantidad;
    });
    tf.textContent=totalfinal;
}

const ajax = (options) => {
    let {method, url, success, error, data} = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", e => {
        if (xhr.readyState !== 4) {
            return;
        }
        if (xhr.status >= 200 && xhr.status < 300) {
            
            if(isJsonObject(xhr.response)){
                let objson = JSON.parse(xhr.response);
                success(objson);
            }
            else{
                success(null);
            }
        } else {
            let message = xhr.statusText || "ocurrio un error";
            error(`Error ${xhr.status} : ${message}`);
        }
    });
    xhr.open(method || "GET", url, true);
    xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
}
function isJsonObject(obj) {
    try {
        JSON.parse(obj);
        console.log("entrro");
    } catch (e) {
        return false;
    }
    return true;
}