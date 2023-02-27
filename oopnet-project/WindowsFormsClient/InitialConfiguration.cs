using DataLayer.Dal;
using System;
using System.Windows.Forms;
using static DataLayer.Models.Enums;

namespace WindowsFormsClient
{
    public partial class InitialConfiguration : Form
    {
        public InitialConfiguration()
        {
            InitializeComponent();
        }

        private void btnStartApp_Click(object sender, EventArgs e)
        {
            string s;
            Gender gender;
            AccessType accessType;
            Language language;

            if (rbMen.Checked)
                s = rbMen.Text;
            else
                s = rbWomen.Text;
            gender = (Gender)Enum.Parse(typeof(Gender), s);

            if (rbFile.Checked)
                s = rbFile.Text;
            else
                s = rbWeb.Text;
            accessType = (AccessType)Enum.Parse(typeof(AccessType), s);

            if (rbCroatian.Checked)
                s = rbCroatian.Text;
            else
                s = rbEnglish.Text;
            language = (Language)Enum.Parse(typeof(Language), s);

            try
            {
                Configuration.SaveConfiguration(gender, accessType, language);
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Cannot save configuration\n\n{ex.Message}");
                Application.Exit();
            }
        }
    }
}
