import React from 'react';

import './MessageList.css';

class MessageList extends React.Component {
    componentDidMount() {
        this.scrollToFreshMessage();
    }

    componentWillReceiveProps(nextProps, nextContext) {
        if (this.props.messages.length !== nextProps.messages.length) {
            this.scrollToFreshMessage();
        }
    }

    scrollToFreshMessage = () => {
        setTimeout(() => {
            const node = document.querySelector('.MessageList');
            node.scrollTo({top: node.scrollHeight});
        }, 10);
    };

    render() {
        const {
            messages,
            isAdmin,

            handleMessageDelete,
        } = this.props;

        return (
            <div className={'MessageList'}>
                {messages.map((message, index) => {
                    return (
                        <div
                            key={index}
                            className={'MessageList-Item'}
                        >
                            <div className={'MessageList-Item-head'}>
                                <div className={'MessageList-Item-username'}>{message.username}</div>
                                {isAdmin ? (<div className={'MessageList-Item-delete'} onClick={() => handleMessageDelete(message.id)}>delete</div>) : null}
                            </div>
                            <div className={'MessageList-Item-messageBody'}>{message.message}</div>
                        </div>
                    )
                })}
            </div>
        );
    }
}

export default MessageList;
