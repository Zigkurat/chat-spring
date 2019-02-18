import React from 'react';

import './MessageForm.css';

class MessageForm extends React.Component {
    render() {
        const {
            value,

            handleSubmit,
            changeMessageField,
        } = this.props;

        return (
            <div className={'MessageForm'}>
                <form onSubmit={handleSubmit}>
                    <input
                        className={'form-control'}
                        value={value}
                        onChange={e => changeMessageField(e.target.value)}
                        required
                    />
                    <button className={'btn btn-primary'} type={'submit'}>SEND</button>
                </form>
            </div>
        );
    }
}

export default MessageForm;
