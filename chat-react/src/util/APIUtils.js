import { API_BASE_URL, ACCESS_TOKEN } from '../constants';

const INTEGER_MAX_VALUE = 2147483647;

//region Http Events
const EVENT_HTTP_STATUS_LOCKED = new CustomEvent('httpEventStatusLocked');
//endregion


const request = (options, headerOpts = {}) => {
    const headerOptions = {
        'Content-Type': 'application/json',
    };
    if (headerOpts['Content-Type'] === undefined) {
        delete headerOpts['Content-Type'];
    }
    const headers = new Headers(headerOptions);
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => {
        if (response.status === 423) {
            window.dispatchEvent(EVENT_HTTP_STATUS_LOCKED);
        }

        return response.json().then(json => {
            if (json.error && json.message) {
                alert(json.message);
            }

            if (!response.ok) {
                return Promise.reject(json);
            }
            return json;
        });
    });
};

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/me",
        method: 'GET'
    });
}

export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

export function signup(signupRequest) {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}

export function updateProfile(updateRequest) {
    return request({
        url: API_BASE_URL + "/user/me",
        method: 'PUT',
        body: JSON.stringify(updateRequest)
    });
}

export function uploadFile(uploadRequest) {
    const formData = new FormData();

    formData.append('image', uploadRequest.image);

    return fetch(API_BASE_URL + "/storage/", {
        method: 'POST',
        body: formData,
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN),
        },
    }).then(response => response.json());
}

//region Rooms
export function getRooms() {
    return request({
        url: API_BASE_URL + `/room/?page=0&size=${INTEGER_MAX_VALUE}`,
        method: 'GET',
    });
}

export function getRoom(roomId) {
    return request({
        url: API_BASE_URL + `/room/${roomId}`,
        method: 'GET',
    });
}

export function updateRoom(roomId, room) {
    return request({
        url: API_BASE_URL + `/room/${roomId}`,
        method: 'PUT',
        body: JSON.stringify(room)
    });
}

export function createRoom(room) {
    return request({
        url: API_BASE_URL + "/room/",
        method: 'POST',
        body: JSON.stringify(room)
    });
}

export function joinRoom(roomId) {
    return request({
        url: API_BASE_URL + "/room/" + roomId + "/join",
        method: 'POST',
    });
}

export function leaveRoom(roomId) {
    return request({
        url: API_BASE_URL + "/room/" + roomId + "/leave",
        method: 'POST',
    });
}

export function deleteRoom(roomId) {
    return request({
        url: API_BASE_URL + "/room/" + roomId,
        method: 'DELETE',
    });
}

export function kickUser(roomId, username) {
    return request({
        url: API_BASE_URL + "/room/" + roomId + "/kick/" + username,
        method: 'POST',
    });
}
//endregion

//region Messages
export function sendMessage(roomId, messageBody) {
    return request({
        url: API_BASE_URL + "/room/" + roomId + "/messages",
        method: 'POST',
        body: JSON.stringify({
            message: messageBody,
        })
    });
}

export function getNewMessages(roomId) {
    return request({
        url: API_BASE_URL + "/room/" + roomId + "/newMessages",
        method: 'GET',
    });
}

export function getMessages(roomId) {
    return request({
        url: API_BASE_URL + "/room/" + roomId + `/messages?size=${INTEGER_MAX_VALUE}&page=0`,
        method: 'GET',
    });
}

export function deleteMessage(messageId) {
    return request({
        url: API_BASE_URL + "/room/messages/" + messageId,
        method: 'DELETE',
    });
}
//endregion

//region Bad Words
export function getBadWords() {
    return request({
        url: API_BASE_URL + `/badword/?page=0&size=${INTEGER_MAX_VALUE}`,
        method: 'GET',
    });
}

export function getBadWord(badWordId) {
    return request({
        url: API_BASE_URL + `/badword/${badWordId}`,
        method: 'GET',
    });
}

export function updateBadWord(badWordId, badWord) {
    return request({
        url: API_BASE_URL + `/badword/${badWordId}`,
        method: 'PUT',
        body: JSON.stringify(badWord)
    });
}

export function createBadWord(badWord) {
    return request({
        url: API_BASE_URL + "/badword/",
        method: 'POST',
        body: JSON.stringify(badWord)
    });
}

export function deleteBadWord(badWordId) {
    return request({
        url: API_BASE_URL + "/badword/" + badWordId,
        method: 'DELETE',
    });
}
//endregion


//region Users
export function getUsers() {
    return request({
        url: API_BASE_URL + `/user/?page=0&size=${INTEGER_MAX_VALUE}`,
        method: 'GET',
    });
}

export function getUser(userId) {
    return request({
        url: API_BASE_URL + `/user/${userId}`,
        method: 'GET',
    });
}

export function updateUser(userId, user) {
    return request({
        url: API_BASE_URL + `/user/${userId}`,
        method: 'PUT',
        body: JSON.stringify(user)
    });
}

export function createUser(user) {
    return request({
        url: API_BASE_URL + "/user/",
        method: 'POST',
        body: JSON.stringify(user)
    });
}

export function deleteUser(userId) {
    return request({
        url: API_BASE_URL + "/user/" + userId,
        method: 'DELETE',
    });
}
//endregion