import React, {Component} from 'react';
import './EditProfile.css';
import {getCurrentUser, updateProfile, uploadFile} from "../../util/APIUtils";

class EditProfile extends Component {
    state = {
        form: {
            username: '',
            email: '',
            password: '',
        },
        previewSrc: null,
    };

    componentDidMount() {
        this.setState({
            form: this.props.currentUser,
            previewSrc: this.props.currentUser.avatarUrl || null,
        });

        getCurrentUser()
            .then(response => {
                this.setState({
                    form: response,
                    previewSrc: response.avatarUrl || null
                });
            });
    }

    handleFieldChange = (event, fieldName) => {
        const form = {...this.state.form};
        form[fieldName] = event.target.value;

        this.setState({
            form,
        });
    };

    doFieldChange = (value, fieldName) => {
        const form = {...this.state.form};
        form[fieldName] = value;

        this.setState({
            form,
        });
    };

    uploadFile = (file) => {
        uploadFile({
            image: file
        }).then(response => {
            this.doFieldChange(response.imageUrl, 'avatarUrl');
        });
    };

    handleAvatarChange = (event) => {
        const file = event.target.files[0];
        const reader = new FileReader();
        let previewSrc = null;

        reader.onloadend = () => {
            previewSrc = reader.result;

            this.uploadFile(file);

            this.setState({
                previewSrc,
            });
        };

        if (file) {
            reader.readAsDataURL(file);
        } else {
            previewSrc = "";
        }
    };

    handleSubmit = (event) => {
        updateProfile(this.state.form).then(() => {
            this.props.history.goBack();
        });

        event.preventDefault();
        event.stopPropagation();
    };

    render() {
        const {
            previewSrc,
            form,
        } = this.state;

        const {
            username,
            email,
            password,
        } = form;

        // console.log(previewSrc);

        const avatarClasses = ['profile-avatar'];
        const avatarStyles = {};
        if (previewSrc) {
            avatarStyles.backgroundImage = `url(${previewSrc})`;
            avatarClasses.push('profile-avatar--with-image');
        }

        return (
            <div className="profile-container">
                <div className="container">
                    <div className="profile-info">
                        <div className={avatarClasses.join(' ')} style={avatarStyles}>
                            { 
                                this.props.currentUser.imageUrl ? (
                                    <img src={this.props.currentUser.imageUrl} alt={this.props.currentUser.username}/>
                                ) : (
                                    <div className="text-avatar">
                                        <span>{this.props.currentUser.username && this.props.currentUser.username[0]}</span>
                                    </div>
                                )
                            }

                            <input type={'file'} onChange={this.handleAvatarChange} />
                        </div>
                        <form
                            onSubmit={this.handleSubmit}
                        >
                            <div className={'EditProfile-Form'}>
                                <div className={'EditProfile-Form-Field form-item'}>
                                    <label htmlFor="username">Username</label>
                                    <input className={'form-control'} required id="username" name={'username'} value={username}
                                           onChange={e => this.handleFieldChange(e, 'username')}/>
                                </div>
                                <div className={'EditProfile-Form-Field form-item'}>
                                    <label htmlFor="email">E-Mail</label>
                                    <input className={'form-control'} required id="email" name={'email'} value={email}
                                           onChange={e => this.handleFieldChange(e, 'email')}/>
                                </div>
                                <div className={'EditProfile-Form-Field form-item'}>
                                    <label htmlFor="password">Password</label>
                                    <input className={'form-control'} id="password" name={'password'} value={password || ''}
                                           onChange={e => this.handleFieldChange(e, 'password')}
                                        type={'password'}
                                    />
                                </div>
                                <div className={'EditProfile-Form-Field'}>
                                    <button type="submit" className="btn btn-block btn-primary" >Save Changes</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>    
            </div>
        );
    }
}

export default EditProfile;