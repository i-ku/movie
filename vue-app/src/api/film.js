import service from "../utils/request";

export function ListHots() {
    return service({
        url: '/film/hot/10',
        method: 'get'
    })
}

export function ListAllFilm() {
    return service({
        url: '/film',
        method: 'get'
    })
}

export function SearchFilm(name) {
    return service({
        url: '/film/name/' + name,
        method: 'get'
    })
}

export function FindFilmByRegionAndType(region, type) {
    return service({
        url: '/film?region=' + region + '&type=' + type,
        method: 'get'
    })
}

export function FindFilmById(id) {
    return service({
        url: '/film/' + id,
        method: 'get'
    })
}

export function AddFilmEvaluate(data) {
    return service({
        url: '/fe',
        method: 'post',
        data: data
    })
}


export function FindAllArrangement() {
    return service({
        url: '/arrangement',
        method: 'get'
    })
}


export function FindFilmEvaluateByFId(id) {
    return service({
        url: '/fe?fid=' + id,
        method: 'get'
    })
}

export function ListAllPoster() {
    return service({
        url: "/poster?status=true",
        method: "get"
    })
}

export function FindArrangements(id) {
    return service({
        url: "/arrangement/film/" + id,
        method: "get"
    })
}

export function FindArrangementById(id) {
    return service({
        url: "/arrangement/" + id,
        method: "get"
    })
}

export function GetArrangementSeats(id) {
    return service({
        url: "/arrangement/getSeats?id=" + id,
        method: "get"
    })
}