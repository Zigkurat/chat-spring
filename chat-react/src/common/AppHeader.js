import React, { Component } from 'react';
import {Link, NavLink} from 'react-router-dom';
import './AppHeader.css';

class AppHeader extends Component {
    render() {
        const {
            isAdmin,
        } = this.props;

        return (
            <header className="app-header">
                <div className="container">
                    <div className="app-branding">
                        <Link to="/" className="app-title">Spring Chat</Link>
                    </div>
                    <div className="app-options">
                        <nav className="app-nav">
                                { this.props.authenticated ? (
                                    <ul>
                                        {isAdmin ? (
                                            <React.Fragment>
                                                <li>
                                                    <NavLink to="/bad-words">Bad Words</NavLink>
                                                </li>
                                                <li>
                                                    <NavLink to="/users">Users</NavLink>
                                                </li>
                                            </React.Fragment>
                                        ) : null}
                                        <li>
                                            <NavLink to="/rooms">Rooms</NavLink>
                                        </li>
                                        <li>
                                            <NavLink to="/profile">Profile</NavLink>
                                        </li>
                                        <li>
                                            <a onClick={this.props.onLogout}>Logout</a>
                                        </li>
                                    </ul>
                                ): (
                                    <ul>
                                        <li>
                                            <NavLink to="/login">Login</NavLink>        
                                        </li>
                                        <li>
                                            <NavLink to="/signup">Signup</NavLink>        
                                        </li>
                                    </ul>
                                )}
                        </nav>
                    </div>
                </div>
            </header>
        )
    }
}

export default AppHeader;