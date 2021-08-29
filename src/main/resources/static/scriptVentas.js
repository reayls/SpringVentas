
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
})

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