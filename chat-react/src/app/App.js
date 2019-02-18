import React, { Component } from 'react';
import {
  Route,
  Switch
} from 'react-router-dom';
import AppHeader from '../common/AppHeader';
import Home from '../home/Home';
import Login from '../user/login/Login';
import Signup from '../user/signup/Signup';
import Profile from '../user/profile/Profile';
import EditProfile from '../user/EditProfile/EditProfile';
import Rooms from '../room/Rooms/Rooms';
import RoomMessages from '../room/RoomMessages/RoomMessages';
import ManageRoom from '../room/ManageRoom/ManageRoom';

import OAuth2RedirectHandler from '../user/oauth2/OAuth2RedirectHandler';
import NotFound from '../common/NotFound';
import LoadingIndicator from '../common/LoadingIndicator';
import { getCurrentUser } from '../util/APIUtils';
import { ACCESS_TOKEN } from '../constants';
import PrivateRoute from '../common/PrivateRoute';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';
import './App.css';
import BadWords from "../badWord/BadWords/BadWords";
import ManageBadWord from "../badWord/ManageBadWord/ManageBadWord";
import ManageUser from "../user/ManageUser/ManageUser";
import Users from "../user/Users/Users";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      currentUser: null,
      loading: false
    }

    this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

    isAdmin = () => {
        const {currentUser} = this.state;

        return currentUser && currentUser.role === "ADMIN";
    };

  loadCurrentlyLoggedInUser() {
    this.setState({
      loading: true
    });

    getCurrentUser()
    .then(response => {
      this.setState({
        currentUser: response,
        authenticated: true,
        loading: false
      });
    }).catch(error => {
      this.setState({
        loading: false
      });  
    });    
  }

  handleLogout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.setState({
      authenticated: false,
      currentUser: null
    });
    Alert.success("You're safely logged out!");
  }

  componentDidMount() {
    this.loadCurrentlyLoggedInUser();
  }

  render() {
    if(this.state.loading) {
      return <LoadingIndicator />
    }

    const isAdmin = this.isAdmin();
    console.log(isAdmin);

    return (
      <div className="app">
        <div className="app-top-box">
          <AppHeader authenticated={this.state.authenticated} isAdmin={this.isAdmin()} onLogout={this.handleLogout} />
        </div>
        <div className="app-body">
          <Switch>
            <Route exact path="/" component={Home}></Route>

              <PrivateRoute path="/users/new" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={ManageUser}></PrivateRoute>
              <PrivateRoute path="/users/:userId/edit" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={ManageUser}></PrivateRoute>
              <PrivateRoute path="/users" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={Users}></PrivateRoute>

              <PrivateRoute path="/bad-words/new" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={ManageBadWord}></PrivateRoute>
              <PrivateRoute path="/bad-words/:badWordId/edit" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={ManageBadWord}></PrivateRoute>
              <PrivateRoute path="/bad-words" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={BadWords}></PrivateRoute>
              <PrivateRoute path="/profile/edit" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={EditProfile}></PrivateRoute>
              <PrivateRoute path="/profile" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={Profile}></PrivateRoute>

              <PrivateRoute path="/rooms/new" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={ManageRoom}></PrivateRoute>
              <PrivateRoute path="/rooms/:roomId/edit" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={ManageRoom}></PrivateRoute>
              <PrivateRoute path="/rooms/:roomId/messages" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={RoomMessages}></PrivateRoute>
              <PrivateRoute path="/rooms" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
                            component={Rooms}></PrivateRoute>
            <Route path="/login"
              render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
            <Route path="/signup"
                   render={(props) => <Signup authenticated={this.state.authenticated} {...props} />}></Route>
            <Route path="/oauth2/redirect" component={OAuth2RedirectHandler}></Route>  
            <Route component={NotFound}></Route>
          </Switch>
        </div>
        <Alert stack={{limit: 3}} 
          timeout = {3000}
          position='top-right' effect='slide' offset={65} />
      </div>
    );
  }
}

export default App;
