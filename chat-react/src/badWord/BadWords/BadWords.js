import React from 'react';
import {deleteBadWord, getBadWords} from "../../util/APIUtils";
import {NavLink} from "react-router-dom";

class BadWords extends React.Component {
    state = {
        badWords: [],
    };

    componentDidMount() {
        this.getBadWords();
    }

    isAdmin = () => {
        const {currentUser} = this.props;

        return currentUser.role === "ADMIN";
    };

    getBadWords = () => {
        getBadWords().then(response => {
            this.setState({
                badWords: response.badWords,
            });
        });
    };

    handleUpdate = (badWordId) => {
        const {
            history,
        } = this.props;

        history.push("/bad-words/" + badWordId + "/edit");
    };

    handleDelete = (badWordId) => {
        const decision = window.confirm('Are you sure you want to delete this bad word?');

        if (decision) {
            deleteBadWord(badWordId).then(() => this.getBadWords());
        }
    };

    handleAddBadWord = () => {
        const {
            history,
        } = this.props;

        history.push("/bad-words/new");
    };

    render() {
        const {
            badWords,
        } = this.state;

        return (
            <div className={'BadWords'}>
                <div className={'container'}>
                    {this.isAdmin() ? (
                        <React.Fragment>
                            <button
                                className="btn btn-primary"
                                style={{marginBottom: 10}}
                                onClick={() => this.handleAddBadWord()}
                            >
                                + ADD NEW
                            </button>
                            <hr />
                        </React.Fragment>
                    ) : null}
                    <table className={'table'} style={{width: '100%', textAlign: 'left'}} cellSpacing={10}>
                        <thead>
                            <tr>
                                <th>Bad Word ID</th>
                                <th>Bad Word Text</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {badWords.map(badWord => {
                                return <tr key={badWord.id}>
                                    <td>{badWord.id}</td>
                                    <td>
                                        <NavLink to={`/bad-words/${badWord.id}/edit`}>{badWord.text}</NavLink>
                                    </td>
                                    <td>
                                        {this.isAdmin() ? (
                                            <React.Fragment>
                                                <button
                                                    className="btn btn-secondary"
                                                    style={{marginRight: 10}}
                                                    onClick={() => this.handleUpdate(badWord.id)}
                                                >
                                                    UPDATE
                                                </button>
                                                <button
                                                    className="btn btn-primary btn-danger"
                                                    onClick={() => this.handleDelete(badWord.id)}
                                                    style={{marginRight: 10}}
                                                >
                                                    DELETE
                                                </button>
                                            </React.Fragment>
                                        ) : null}
                                    </td>
                                </tr>;
                            })}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default BadWords;
