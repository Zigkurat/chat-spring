import React, {Component} from 'react';
import './Profile.css';
import {NavLink} from "react-router-dom";
import {getCurrentUser} from "../../util/APIUtils";

class Profile extends Component {
    state = {
        currentUser: this.props.currentUser
    };

    componentDidMount() {
        getCurrentUser()
            .then(response => {
                this.setState({
                    currentUser: response,
                });
            });
    }

    render() {
        return (
            <div className="profile-container">
                <div className="container">
                    <div className="profile-info">
                        <div className="profile-avatar">
                            {
                                this.state.currentUser.avatarUrl ? (
                                    <img src={this.state.currentUser.avatarUrl} alt={this.state.currentUser.username}/>
                                ) : (
                                    <div className="text-avatar">
                                        <span>{this.state.currentUser.username && this.state.currentUser.username[0]}</span>
                                    </div>
                                )
                            }
                        </div>
                        <div className="profile-name">
                            <h2>{this.state.currentUser.username}</h2>
                            <p className="profile-email">{this.state.currentUser.email}</p>
                        </div>
                        <div className="profile-actions">
                            <NavLink to={'/profile/edit'}>Edit</NavLink>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile