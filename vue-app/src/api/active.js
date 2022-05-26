import service from "../utils/request";

export function ListAllActivity() {
    return service({
        url: "/activity",
        method: "get"
    })
}

export function SubmitActive(data) {
    return service({
        url: '/registration',
        method: 'post',
        data: data
    })
}