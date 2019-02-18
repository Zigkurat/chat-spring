import React from 'react';
import {createUser, getUser, updateUser, uploadFile} from "../../util/APIUtils";

const Roles = ['ADMIN', 'USER'];

class ManageUser extends React.Component {
    state = {
        previewSrc: null,
        form: {
            username: '',
            email: '',
            password: '',
            role: '',
        },
    };

    componentDidMount() {
        const {
            userId,
        } = this.props.match.params;

        if (userId) {
            this.getUser(userId);
        }
    }

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

    getUser = (userId) => {
        getUser(userId).then(response => {
            this.setState({
                form: response,
                previewSrc: response.avatarUrl,
            });
        });
    };

    updateUser = (userId, formValues) => {
        const {
            history,
        } = this.props;

        updateUser(userId, formValues).then(() => {
            history.goBack();
        });
    };

    createUser = (formValues) => {
        const {
            history,
        } = this.props;

        createUser(formValues).then(() => {
            history.goBack();
        });
    };

    handleFieldChange = (event, fieldName) => {
        const form = {...this.state.form};
        form[fieldName] = event.target.value;

        this.setState({
            form,
        });
    };

    handleSubmit = (event) => {
        if (this.props.match.params.userId) {
            this.updateUser(this.props.match.params.userId, this.state.form);
        }
        else {
            this.createUser(this.state.form);
        }

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
            role,
        } = form;

        const avatarClasses = ['profile-avatar'];
        const avatarStyles = {};
        if (previewSrc) {
            avatarStyles.backgroundImage = `url(${previewSrc})`;
            avatarClasses.push('profile-avatar--with-image');
        }

        return (
            <div className={'ManageUser'}>
                <div className={'container'}>
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
                                       type="password" onChange={e => this.handleFieldChange(e, 'password')}/>
                            </div>
                            <div className={'EditProfile-Form-Field form-item'}>
                                <label htmlFor="role">Password</label>
                                <select
                                    className={'form-control'}
                                    required
                                    id="role"
                                    name={'role'}
                                    onChange={e => this.handleFieldChange(e, 'role')}
                                    value={role}
                                >
                                    {Roles.map(ROLE => {
                                        return (
                                            <option key={ROLE} value={ROLE}>{ROLE}</option>
                                        );
                                    })}
                                </select>
                            </div>
                            <div className={'EditProfile-Form-Field'}>
                                <button type="submit" className="btn btn-block btn-primary" >Save Changes</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default ManageUser;
