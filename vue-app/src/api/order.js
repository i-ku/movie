import service from "../utils/request";

export function CreateOrder(data) {
    return service({
        url: "/order",
        method: "post",
        data: data
    })
}

export function FindOrderByUser(id) {
    return service({
        url: "/order/user/" + id,
        method: "get"
    })
}

export function PayForOrder(id) {
    return service({
        url: '/order/pay?id=' + id,
        method: "get"
    })
}