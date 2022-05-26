import request from "axios"
import config from "@/config"
import {Notification} from 'element-ui'

const uid = localStorage.getItem('uid');
const token = localStorage.getItem('token');

const service = request.create({
    baseURL: config.API_URL,
    headers: {
        "Authorization": token
    }
});

service.interceptors.response.use(
    response => {
        const res = response.data;
        if (res.success === true) {
            if (res.msg !== null) {
                Notification.success({
                    title: 'Success ',
                    message: res.msg,
                    type: 'success'
                });
            }

        } else {
            Notification.error({
                title: '错误提示: ' + res.code,
                message: res.msg
            });
        }
        return res
    },

    error => {
        console.log('err' + error);
        return Promise.reject(error)
    }
);

export default service
