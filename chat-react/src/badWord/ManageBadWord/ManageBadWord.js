import React from 'react';
import {createBadWord, getBadWord, updateBadWord} from "../../util/APIUtils";

class ManageBadWord extends React.Component {
    state = {
        form: {
            text: '',
        },
    };

    componentDidMount() {
        const {
            badWordId,
        } = this.props.match.params;

        if (badWordId) {
            this.getBadWord(badWordId);
        }
    }

    getBadWord = (badWordId) => {
        getBadWord(badWordId).then(response => {
            this.setState({
                form: response,
            });
        });
    };

    updateBadWord = (badWordId, formValues) => {
        const {
            history,
        } = this.props;

        updateBadWord(badWordId, formValues).then(() => {
            history.goBack();
        });
    };

    createBadWord = (formValues) => {
        const {
            history,
        } = this.props;

        createBadWord(formValues).then(() => {
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
        if (this.props.match.params.badWordId) {
            this.updateBadWord(this.props.match.params.badWordId, this.state.form);
        }
        else {
            this.createBadWord(this.state.form);
        }

        event.preventDefault();
        event.stopPropagation();
    };

    render() {
        const {
            form,
        } = this.state;

        const {
            text,
        } = form;

        return (
            <div className={'ManageBadWord'}>
                <div className={'container'}>
                    <form
                        onSubmit={this.handleSubmit}
                    >
                        <div className={'EditProfile-Form'}>
                            <div className={'EditProfile-Form-Field form-item'}>
                                <label htmlFor="text">Word</label>
                                <input className={'form-control'} required id="text" name={'text'} value={text}
                                       onChange={e => this.handleFieldChange(e, 'text')}/>
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

export default ManageBadWord;
