import React from 'react';
import {createRoom, getRoom, updateRoom} from "../../util/APIUtils";

class ManageRoom extends React.Component {
    state = {
        form: {
            name: '',
        },
    };

    componentDidMount() {
        const {
            roomId,
        } = this.props.match.params;

        if (roomId) {
            this.getRoom(roomId);
        }
    }

    getRoom = (roomId) => {
        getRoom(roomId).then(response => {
            this.setState({
                form: response,
            });
        });
    };

    updateRoom = (roomId, formValues) => {
        const {
            history,
        } = this.props;

        updateRoom(roomId, formValues).then(() => {
            history.goBack();
        });
    };

    createRoom = (formValues) => {
        const {
            history,
        } = this.props;

        createRoom(formValues).then(() => {
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
        if (this.props.match.params.roomId) {
            this.updateRoom(this.props.match.params.roomId, this.state.form);
        }
        else {
            this.createRoom(this.state.form);
        }

        event.preventDefault();
        event.stopPropagation();
    };

    render() {
        const {
            form,
        } = this.state;

        const {
            name,
        } = form;

        return (
            <div className={'ManageRoom'}>
                <div className={'container'}>
                    <form
                        onSubmit={this.handleSubmit}
                    >
                        <div className={'EditProfile-Form'}>
                            <div className={'EditProfile-Form-Field form-item'}>
                                <label htmlFor="name">Name</label>
                                <input className={'form-control'} required id="name" name={'name'} value={name}
                                       onChange={e => this.handleFieldChange(e, 'name')}/>
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

export default ManageRoom;
