import React from 'react';

import MessageList from "../MessageList/MessageList";
import MessageForm from "../MessageForm/MessageForm";
import {deleteMessage, getMessages, getNewMessages, sendMessage} from "../../util/APIUtils";

class RoomMessages extends React.Component {
    state = {
        currentMessage: "",
        messages: [],
    };

    messagePollingIntervalId = null;

    componentDidMount() {
        this.getMessages().then(() => {
            this.messagePollingIntervalId = setInterval(() => {
                this.getNewMessages();
            }, 1000);
        });

        window.addEventListener('httpEventStatusLocked', () => {
            this.props.history.goBack();
        });
    }

    isAdmin = () => {
        const {currentUser} = this.props;

        return currentUser.role === "ADMIN";
    };

    componentWillUnmount() {
        clearInterval(this.messagePollingIntervalId);
    }

    changeMessageField = (currentMessage) => {
        this.setState({
            currentMessage,
        });
    };

    getNewMessages = () => {
        getNewMessages(this.props.match.params.roomId).then(response => {
            const messages = [...this.state.messages, ...response.messages];

            this.setState({
                messages,
            });
        });
    };

    getMessages = () => {
        return getMessages(this.props.match.params.roomId).then(response => {
            this.setState({
                messages: response.messages,
            });
        });
    };

    handleSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();

        sendMessage(this.props.match.params.roomId, this.state.currentMessage).then(() => {
            this.resetForm();
        });
    };

    resetForm = () => {
        this.setState({
            currentMessage: "",
        });
    };

    handleMessageDelete = (messageId) => {
        const decision = window.confirm('Are you sure you want to delete this message?');

        if (decision) {
            deleteMessage(messageId).then(() => {
                const messages = this.state.messages.filter(message => message.id !== messageId);

                this.setState({
                    messages,
                });
            });
        }
    };

    render() {
        const {
            currentMessage,
            messages,
        } = this.state;

        return (
            <div className={'RoomMessages'}>
                <div className={'container'}>
                    <MessageList
                        isAdmin={this.isAdmin()}
                        messages={messages}
                        handleMessageDelete={this.handleMessageDelete}
                    />
                    <MessageForm
                        value={currentMessage}
                        changeMessageField={this.changeMessageField}
                        handleSubmit={this.handleSubmit}
                    />
                </div>
            </div>
        );
    }
}

export default RoomMessages;
