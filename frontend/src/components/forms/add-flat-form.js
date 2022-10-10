import FlatForm from "./general/flat-form";
import {useSnackbar} from "notistack";
import {useState} from "react";
import {Button} from "antd";
import {parseFlatFromXML, parseFlatToXML} from "../../utils/parsers/xml/flat-parser";
import axios from "axios";
import {FLATS_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";

export function AddFlatForm() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [isAddFlatModalOpen, setIsAddFlatModalOpen] = useState(false);

    const showModifyFlatModal = () => {
        setIsAddFlatModalOpen(true);
    };

    const handleAddFlatOk = () => {
        setIsAddFlatModalOpen(false);
    };

    const handleAddFlatCancel = () => {
        setIsAddFlatModalOpen(false);
    };

    const onFormSubmit = (e) => {
        const body = parseFlatToXML(e);

        axios.post(FLATS_API, body, {
                headers: {
                    'content-type': 'application/xml'
                }
            }
        )
            .then((response) => {
                const newFlat = parseFlatFromXML(response.data)
                enqueueSnackbar("Created new flat with id: " + newFlat.id, {
                    autoHideDuration: 5000,
                    variant: "success"
                })
                setIsAddFlatModalOpen(false);
            })
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            });
    }

    return (
        <>
            <Button type="primary" onClick={showModifyFlatModal} style={{width: 150, flex: 1}}>
                Add new flat
            </Button>
            <FlatForm
                formVisible={isAddFlatModalOpen}
                onCancel={handleAddFlatCancel}
                onFinish={onFormSubmit}
                title="Modify flat"
            />
        </>
    )
}