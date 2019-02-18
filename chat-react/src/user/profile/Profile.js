import React, { Component } from 'react';
import './Profile.css';
import {NavLink} from "react-router-dom";

class Profile extends Component {
    render() {
        return (
            <div className="profile-container">
                <div className="container">
                    <div className="profile-info">
                        <div className="profile-avatar">
                            { 
                                this.props.currentUser.avatarUrl ? (
                                    <img src={this.props.currentUser.avatarUrl} alt={this.props.currentUser.username}/>
                                ) : (
                                    <div className="text-avatar">
                                        <span>{this.props.currentUser.username && this.props.currentUser.username[0]}</span>
                                    </div>
                                )
                            }
                        </div>
                        <div className="profile-name">
                           <h2>{this.props.currentUser.username}</h2>
                           <p className="profile-email">{this.props.currentUser.email}</p>
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