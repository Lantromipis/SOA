import {useSnackbar} from "notistack";
import {useState} from "react";
import axios from "axios";
import {GET_CHEAPEST} from "../../utils/api-constancts";
import {parseFlatFromXML} from "../../utils/parsers/xml/flat-parser";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {Button, Form, InputNumber} from "antd";
import {SimpleFlatResponseModel} from "./general/simple-flat-response-model";
import {validateIntegerGreaterThanZero} from "../forms/validators";
import {useForm} from "antd/es/form/Form";

export function GetCheapestModal(){
    const [form] = useForm();

    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [modalVisible, setModalVisible] = useState(false);
    const [modalValue, setModalValue] = useState();

    const handelOpen = (e) => {
        axios.get(`${GET_CHEAPEST}/${e['id1']}/${e['id2']}`)
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
            <Form
                form={form}
                onFinish={handelOpen}
                layout="inline"
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
            >
                <Form.Item
                    style={{width: 200}}
                    label="ID#1"
                    name="id1"
                    rules={[
                        {required: true, message: 'Please input ID#1!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item
                    style={{width: 200}}
                    label="ID#2"
                    name="id2"
                    rules={[
                        {required: true, message: 'Please input ID#2!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={form.submit} style={{width: 150}}>
                        Get cheapest
                    </Button>
                </Form.Item>
            </Form>
            <SimpleFlatResponseModel
                title="Calculation result"
                visible={modalVisible}
                value={modalValue}
                handleOk={handelModalOk}
            />
        </>
    )
}