using DataLayer.Dal;
using System;
using System.Windows.Forms;
using static DataLayer.Models.Enums;

namespace WindowsFormsClient
{
    public partial class Settings : Form
    {
        private Gender gender;
        private AccessType accessType;
        private Language language;

        public Settings(Gender gender, AccessType accessType, Language language)
        {
            this.gender = gender;
            this.accessType = accessType;
            this.language = language;
            
            InitializeComponent();
            SetValues();
        }

        private void SetValues()
        {
            if (gender == Gender.Men)
            {
                rbMen.Checked = true;
            }
            else
            {
                rbWomen.Checked = true;
            }

            if (accessType == AccessType.File)
            {
                rbFile.Checked = true;
            }
            else
            {
                rbWeb.Checked = true;
            }

            if (language == Language.Croatian)
            {
                rbCroatian.Checked = true;
            }
            else
            {
                rbEnglish.Checked = true;
            }
        }

        private void btnOK_Click(object sender, EventArgs e)
        {
            string s;
            string[] settings = new string[3];

            if (rbMen.Checked)
                s = rbMen.Tag.ToString();
            else
                s = rbWomen.Tag.ToString();
            gender = (Gender)Enum.Parse(typeof(Gender), s);
            settings[0] = s;

            if (rbFile.Checked)
                s = rbFile.Tag.ToString();
            else
                s = rbWeb.Tag.ToString();
            accessType = (AccessType)Enum.Parse(typeof(AccessType), s);
            settings[1] = s;

            if (rbCroatian.Checked)
                s = rbCroatian.Tag.ToString();
            else
                s = rbEnglish.Tag.ToString();
            language = (Language)Enum.Parse(typeof(Language), s);
            settings[2] = s;

            btnOK.Tag = string.Join(",", settings);

            if (MessageBox.Show("Do you want to save settings?", "Confirm settings", MessageBoxButtons.YesNo) == DialogResult.Yes)
            {
                try
                {
                    Configuration.SaveConfiguration(gender, accessType, language);
                    DialogResult = DialogResult.OK;
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Cannot save configuration\n\n{ex.Message}");
                    Application.Exit();
                }
            }
        }

        private void btnCancel_Click(object sender, EventArgs e) {
            DialogResult = DialogResult.Cancel;
        }
    }
}
