import service from "../utils/request";

export function CreateCart(data) {
    return service({
        url: "/cart",
        method: "post",
        data: data
    })
}

export function ListCarts(id) {
    return service({
        url: '/cart?uid=' + id,
        method: 'get'
    })
}

export function DeleteCartById(id) {
    return service({
        url: '/cart?id=' + id,
        method: "delete"
    })
}