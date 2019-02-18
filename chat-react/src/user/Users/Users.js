import React from 'react';
import {deleteUser, getUsers} from "../../util/APIUtils";
import {NavLink} from "react-router-dom";

class Users extends React.Component {
    state = {
        users: [],
    };

    componentDidMount() {
        this.getUsers();
    }

    isAdmin = () => {
        const {currentUser} = this.props;

        return currentUser.role === "ADMIN";
    };

    getUsers = () => {
        getUsers().then(response => {
            this.setState({
                users: response.users,
            });
        });
    };

    handleUpdate = (userId) => {
        const {
            history,
        } = this.props;

        history.push("/users/" + userId + "/edit");
    };

    handleDelete = (userId) => {
        const decision = window.confirm('Are you sure you want to delete this user?');

        if (decision) {
            deleteUser(userId).then(() => this.getUsers());
        }
    };

    handleAddUser = () => {
        const {
            history,
        } = this.props;

        history.push("/users/new");
    };

    render() {
        const {
            users,
        } = this.state;

        const avatarStyles = {
            width: 50,
            height: 50,
            objectFit: 'cover',
            objectPosition: 'center',
            borderRadius: '50%',
        };

        return (
            <div className={'Users'}>
                <div className={'container'}>
                    {this.isAdmin() ? (
                        <React.Fragment>
                            <button
                                className="btn btn-primary"
                                style={{marginBottom: 10}}
                                onClick={() => this.handleAddUser()}
                            >
                                + ADD NEW
                            </button>
                            <hr />
                        </React.Fragment>
                    ) : null}
                    <table className={'table'} style={{width: '100%', textAlign: 'left'}} cellSpacing={10}>
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>User Avatar</th>
                                <th>User Name</th>
                                <th>User E-Mail</th>
                                <th>User Role</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map(user => {
                                return <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>
                                        <a href={user.avatarUrl} target={'_blank'}>
                                            <img src={user.avatarUrl} style={avatarStyles} />
                                        </a>
                                    </td>
                                    <td>
                                        <NavLink to={`/users/${user.id}/edit`}>{user.username}</NavLink>
                                    </td>
                                    <td>
                                        {user.email}
                                    </td>
                                    <td>
                                        {user.role}
                                    </td>
                                    <td>
                                        {this.isAdmin() ? (
                                            <React.Fragment>
                                                <button
                                                    className="btn btn-secondary"
                                                    style={{marginRight: 10}}
                                                    onClick={() => this.handleUpdate(user.id)}
                                                >
                                                    UPDATE
                                                </button>
                                                <button
                                                    className="btn btn-primary btn-danger"
                                                    onClick={() => this.handleDelete(user.id)}
                                                    style={{marginRight: 10}}
                                                >
                                                    DELETE
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

export default Users;
