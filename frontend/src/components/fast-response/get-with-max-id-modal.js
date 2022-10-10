import {useSnackbar} from "notistack";
import {useState} from "react";
import axios from "axios";
import {GET_WITH_MAX_ID_API} from "../../utils/api-constancts";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {Button} from "antd";
import {SimpleResponseModel} from "./general/simple-response-modal";
import {parseFlatFromXML} from "../../utils/parsers/xml/flat-parser";
import {SimpleFlatResponseModel} from "./general/simple-flat-response-model";

export function GetWithMaxIdModal(){
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [modalVisible, setModalVisible] = useState(false);
    const [modalValue, setModalValue] = useState();

    const handelOpen = () => {
        axios.get(GET_WITH_MAX_ID_API)
            .then((response) => {
                const parsedResponse = parseFlatFromXML(response.data)
                setModalValue(parsedResponse)
                setModalVisible(true);
            })
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            });
    }

    const handelModalOk = () =>{
        setModalVisible(false)
    }

    return (
        <>
            <Button type="primary" onClick={handelOpen}>
                Get with max ID
            </Button>
            <SimpleFlatResponseModel
                title="Calculation result"
                visible={modalVisible}
                value={modalValue}
                handleOk={handelModalOk}
            />
        </>
    )
}