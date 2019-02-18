import React from 'react';
import {deleteRoom, getRooms, joinRoom, kickUser, leaveRoom} from "../../util/APIUtils";
import {NavLink} from "react-router-dom";

class Rooms extends React.Component {
    state = {
        rooms: [],
    };

    componentDidMount() {
        this.getRooms();
    }

    isAdmin = () => {
        const {currentUser} = this.props;

        return currentUser.role === "ADMIN";
    };

    getRooms = () => {
        getRooms().then(response => {
            this.setState({
                rooms: response.rooms,
            });
        });
    };

    goToMessages = (roomId) => {
        const {
            history,
        } = this.props;

        history.push("/rooms/" + roomId + "/messages");
    };

    handleJoin = (roomId) => {
        joinRoom(roomId).then(() => {
            this.goToMessages(roomId);
        });
    };

    handleLeave = (roomId) => {
        leaveRoom(roomId).then(() => {
            this.getRooms();
        });
    };

    handleUpdate = (roomId) => {
        const {
            history,
        } = this.props;

        history.push("/rooms/" + roomId + "/edit");
    };

    handleDelete = (roomId) => {
        const decision = window.confirm('Are you sure you want to delete this room?');

        if (decision) {
            deleteRoom(roomId).then(() => this.getRooms());
        }
    };

    handleAddRoom = () => {
        const {
            history,
        } = this.props;

        history.push("/rooms/new");
    };

    handleKickUser = (roomId) => {
        const username = window.prompt("Type {username} to kick from room.");

        if (username && username.trim()) {
            kickUser(roomId, username.trim());
        }
    };

    render() {
        const {
            rooms,
        } = this.state;

        return (
            <div className={'Rooms'}>
                <div className={'container'}>
                    {this.isAdmin() ? (
                        <React.Fragment>
                            <button
                                className="btn btn-primary"
                                style={{marginBottom: 10}}
                                onClick={() => this.handleAddRoom()}
                            >
                                + ADD NEW
                            </button>
                            <hr />
                        </React.Fragment>
                    ) : null}
                    <table className={'table'} style={{width: '100%', textAlign: 'left'}} cellSpacing={10}>
                        <thead>
                            <tr>
                                <th>Room ID</th>
                                <th>Room Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rooms.map(room => {
                                return <tr key={room.id}>
                                    <td>{room.id}</td>
                                    <td>
                                        {room.joined ? (
                                            <NavLink to={`/rooms/${room.id}/messages`}>{room.name}</NavLink>
                                        ) : room.name}
                                    </td>
                                    <td>
                                        {room.joined ? (
                                            <button
                                                className="btn btn-danger"
                                                style={{marginRight: 10}}
                                                onClick={() => this.handleLeave(room.id)}
                                            >
                                                LEAVE
                                            </button>
                                        ) : (
                                            <button
                                                className="btn btn-primary"
                                                style={{marginRight: 10}}
                                                onClick={() => this.handleJoin(room.id)}
                                            >
                                                JOIN
                                            </button>
                                        )}
                                        {this.isAdmin() ? (
                                            <React.Fragment>
                                                <button
                                                    className="btn btn-secondary"
                                                    style={{marginRight: 10}}
                                                    onClick={() => this.handleUpdate(room.id)}
                                                >
                                                    UPDATE
                                                </button>
                                                <button
                                                    className="btn btn-primary btn-danger"
                                                    onClick={() => this.handleDelete(room.id)}
                                                    style={{marginRight: 10}}
                                                >
                                                    DELETE
                                                </button>
                                                <button
                                                    className="btn btn-primary btn-danger"
                                                    onClick={() => this.handleKickUser(room.id)}
                                                >
                                                    KICK USER
                                                </button>
                                            </React.Fragment>
                                        ) : null}
                                    </td>
                                </tr>;
                            })}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default Rooms;
