import {Button, Form, InputNumber} from "antd";
import {validateIntegerGreaterThanZero} from "./validators";
import {useForm} from "antd/es/form/Form";
import {useState} from "react";
import FlatForm from "./general/flat-form";
import axios from "axios";
import {FLATS_API} from "../../utils/api-constancts";
import {parseFlatFromXML, parseFlatToXML} from "../../utils/parsers/xml/flat-parser";
import {parseError} from "../../utils/parsers/xml/error-parser";
import {useSnackbar} from "notistack";

export function ModifyFlatForm() {
    const {enqueueSnackbar, closeSnackbar} = useSnackbar();

    const [modifyForm] = useForm();
    const [isModifyFlatModalOpen, setIsModifyFlatModalOpen] = useState(false);
    const [initialValues, setInitialValues] = useState(undefined);
    const [flatId, setFlatId] = useState(undefined);

    const showModifyFlatModal = (e) => {
        axios
            .get(`${FLATS_API}/${e['id']}`)
            .then((response) => {
                const parsedResponse = parseFlatFromXML(response.data)
                setInitialValues(parsedResponse)
                setIsModifyFlatModalOpen(true);
                setFlatId(e['id'])
            })
            .catch((err) => {
                let error = parseError(err.response.data)
                enqueueSnackbar(error.message, {
                    autoHideDuration: 5000,
                    variant: "error"
                })
            });
    };

    const handleFormSubmit = (e) => {
        const body = parseFlatToXML(e);

        if (flatId) {
            axios.put(`${FLATS_API}/${flatId}`, body, {
                    headers: {
                        'content-type': 'application/xml'
                    }
                }
            )
                .then((response) => {
                    const newFlat = parseFlatFromXML(response.data)
                    enqueueSnackbar("Successfully modified flat with id: " + newFlat.id, {
                        autoHideDuration: 5000,
                        variant: "success"
                    })
                    setIsModifyFlatModalOpen(false);
                })
                .catch((err) => {
                    let error = parseError(err.response.data)
                    enqueueSnackbar(error.message, {
                        autoHideDuration: 5000,
                        variant: "error"
                    })
                });
        }
    };

    const handleModifyFlatCancel = () => {
        setIsModifyFlatModalOpen(false);
    };

    return (
        <>
            <Form
                form={modifyForm}
                onFinish={showModifyFlatModal}
                labelCol={{span: 8}}
                wrapperCol={{span: 16}}
                layout="inline"
            >
                <Form.Item
                    style={{width: 200}}
                    label="ID"
                    name="id"
                    rules={[
                        {required: true, message: 'Please input ID!'},
                        {validator: validateIntegerGreaterThanZero}
                    ]}
                >
                    <InputNumber/>
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={modifyForm.submit} style={{width: 150}}>
                        Modify flat
                    </Button>
                </Form.Item>
            </Form>

            <FlatForm
                formVisible={isModifyFlatModalOpen}
                onCancel={handleModifyFlatCancel}
                onFinish={handleFormSubmit}
                title="Modify flat"
                initialValues={initialValues}
            />
        </>
    )
}